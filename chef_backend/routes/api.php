<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\api\ChefRentController;

Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');

Route::get("/chef-rents", [ChefRentController::class, "getChefRents"]);
Route::post("/chef-rents", [ChefRentController::class, "addChefRent"]);
Route::put("/chef-rents/{rent}", [ChefRentController::class, "updateChefRent"]);
Route::delete("/chef-rents/{rent}", [ChefRentController::class, "deleteChefRent"]);