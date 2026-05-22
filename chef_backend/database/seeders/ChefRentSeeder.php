<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class ChefRentSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        $filePath = database_path('chef_berlesek_2025.csv');
        $file = fopen($filePath, 'r');
        $header = fgetcsv($file);
        while ($row = fgetcsv($file)) {
            $data = array_combine($header, $row);
            DB::table('chefrents')->insert([
                'chefId' => $data['chefid'],
                'startDate' => $data['startdate'],
                'endDate' => $data['enddate'],
                'dailyRate' => $data['daily_rate'],
                'name' => $data['name'],
                'cuisine' => $data['cuisine'],
            ]);
        }
        fclose($file);
    }
}
