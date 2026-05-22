<?php

namespace App\Models;
use Illuminate\Database\Eloquent\Casts\Attribute;
use Illuminate\Database\Eloquent\Model;
use Carbon\Carbon;

class ChefRent extends Model
{
    protected $table = 'chefrents';
    protected $fillable = [
        'uid', 'chefId', 'startDate', 'endDate', 'dailyRate', 'cuisine'
    ];
    protected $casts = [
        'startDate' => 'datetime',
        'endDate' => 'datetime',
    ];
    protected $appends = ['total_price'];

    protected $primaryKey = 'uid';

    protected function totalPrice(): Attribute
    {
        return Attribute::make(
            get: function () {
                if (!$this->startDate || !$this->endDate || !$this->dailyRate) {
                    return 0;
                }
            $days = Carbon::parse($this->startDate)->diffInDays(Carbon::parse($this->endDate));
                
            return $days * $this->dailyRate;
        });
    }
}
