<?php

namespace App\Http\Requests;

use Illuminate\Contracts\Validation\ValidationRule;
use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Validation\Validator;
use Carbon\Carbon;
use DB;

class UpdateChefRentRequest extends FormRequest
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
            'chefId' => 'required|integer',
            'dailyRate' => 'required|integer|min:0',
            'startDate' => 'required|date|after_or_equal:tomorrow',
            'endDate' => 'required|date|after:startDate',
            "name" => "required|string",
            "cuisine" => "required|string"
        ];
    }
    
    public function messages(): array
    {
        return [
           'chefId.required' => 'Séf azonosítójának megadása kötelező.',
            'dailyRate.required' => 'A napi ár megadása kötelezező.',
            'startDate.required' => 'A megadott időszak megadása kötelező.',
            'startDate.after_or_equal' => 'A megadott időszak nem érvényes',
            'endDate.required' => 'A megadott időszak nem lehet korábbi a kezdeti időszaknál.',
            "name.required" => "A megadott nev megadása kötelező.",
            "cuisine.required" => "A megadott kuriúzomvilág megadása kötelező."
        ];
    }

}
