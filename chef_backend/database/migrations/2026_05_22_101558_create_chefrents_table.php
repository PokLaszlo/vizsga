<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('chefrents', function (Blueprint $table) {
            $table->id("uid");
            $table->integer('chefId');
            $table->dateTime("startDate");
            $table->dateTime("endDate");
            $table->integer("dailyRate");
            $table->string("cuisine");
            $table->string("name");
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('chefrents');
    }
};
