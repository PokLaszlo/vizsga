<?php

namespace App\Http\Controllers\api;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Http\Requests\ChefRentRequest;
use App\Http\Requests\UpdateChefRentRequest;
use App\Models\ChefRent;
use Carbon\Carbon;

class ChefRentController extends Controller
{
    public function getChefRents(){
        $rents = ChefRent::all();
        return response()->json([
            'success' => true,
            "message" => "Sikeres lekérdezés",
            'data' => $rents]);
    }
    public function addChefRent(ChefRentRequest $request){
        $validated = $request->validated();

        $rent = new ChefRent();
        // $rent->uid = $validated['uid'];
        $rent->chefId = $validated['chefId'];
        $rent->dailyRate = $validated['dailyRate'];
        $rent->startDate = Carbon::parse($validated['startDate']);
        $rent->endDate = Carbon::parse($validated['endDate']);
        $rent->name = $validated['name'];
        $rent->cuisine = $validated['cuisine'];

        $rent->save();

        return response()->json([
            'success' => true,
            "message" => "Sikeres hozzáadás",
            'data' => $rent]);
    }

    public function updateChefRent(UpdateChefRentRequest $request, $uid){
        $validated = $request->validated();
        $rent = ChefRent::find($uid);
        $rent->chefId = $validated['chefId'];
        $rent->dailyRate = $validated['dailyRate'];
        $rent->startDate = Carbon::parse($validated['startDate']);
        $rent->endDate = Carbon::parse($validated['endDate']);
        $rent->name = $validated['name'];
        $rent->cuisine = $validated['cuisine'];

        $rent->save();

        return response()->json([
            'success' => true,
            "message" => "Sikeres frissítés",
            'data' => $rent]);
    }

    public function deleteChefRent($id){
        $rent = ChefRent::find($id);
        $rent->delete();
        return response()->json([
            'success' => true,
            "message" => "Sikeres törlés",
            'data' => $rent]);
    }

}
