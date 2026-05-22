<?php

namespace App\Http\Requests;

use Illuminate\Contracts\Validation\ValidationRule;
use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Validation\Validator;
use Carbon\Carbon;
use DB;

class ChefRentRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     */
    public function authorize(): bool
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array<string, ValidationRule|array<mixed>|string>
     */
    public function rules(): array
    {
        return [
            // 'uid' => 'required|integer',
            'chefId' => 'required|integer',
            'dailyRate' => 'required|integer|min:0',
            'startDate' => 'required|date|after_or_equal:tomorrow',
            'endDate' => 'required|date|after:startDate',
            "name" => "required|string",
            "cuisine" => "required|string"
        ];
    }
    public function withValidator(Validator $validator): void
    {

        $validator->after(function ($validator) {
            if ($validator->errors()->has('startDate') || $validator->errors()->has('endDate')) {
            return;
        }

         $startDate = Carbon::parse($this->input('startDate'));
         $endDate = Carbon::parse($this->input('endDate'));
        
         $days = $startDate->diffInDays($endDate);

            if ($days < 3) {
                $validator->errors()->add('endDate', 'A bérlés időtartama legalább 3 nap kell, hogy legyen.');
            }

            if ($days > 14) {
                $validator->errors()->add('endDate', 'A bérlés időtartama legfeljebb 14 nap lehet.');
            }
            $chefId = $this->input('chefId');
            
            $hasOverlap = DB::table('chefrents')
                ->where('chefId', $chefId)
                ->where(function ($query) use ($startDate, $endDate) {
                    $query->whereBetween('startDate', [$startDate, $endDate])
                          ->orWhereBetween('endDate', [$startDate, $endDate])
                          ->orWhere(function ($q) use ($startDate, $endDate) {
                              $q->where('startDate', '<=', $startDate)
                                ->where('endDate', '>=', $endDate);
                          });
                })
                ->exists();

            if ($hasOverlap) {
                $validator->errors()->add('chefId', 'Ez a séf a megadott időszakban már foglalt.');
            }
        });
    }
    public function messages(): array
    {
        return [
            // 'uid.required' => 'Felhasználó azonosítójának megadása kötelező.',
            'chefId.required' => 'Séf azonosítójának megadása kötelező.',
            'dailyRate.required' => 'A napi ár megadása kötelezező.',
            'startDate.required' => 'A megadott időszak megadása kötelező.',
            'startDate.after_or_equal' => 'A megadott időszak nem érvényes',
            'endDate.required' => 'A megadott időszak nem lehet korábbi a kezdeti időszaknál.',
            "name.required" => "A megadott nev megadása kötelező.",
            "cuisine.required" => "A megadott kuriúzomvilág megadása kötelező."
        ];
    }
    // protected function failedValidation(Validator $validator)
    // {
    //     throw new HttpResponseException(
    //         response()->json([
    //             'success' => false,
    //             'message' => 'Validációs hiba történt.',
    //             'errors'  => $validator->errors()
    //         ], 400)
    //     );
    // }
}
