package ru.cs.vsu.yachnyy_m_a;

import java.util.HashMap;
import java.util.List;

public class Task10 {
    public static HashMap<String, Flat> process(List<Flat> flats, int required_room_count, int min_area){
        HashMap<String, Flat> result = new HashMap<>();
        for(Flat flat: flats){
            String district = flat.district;
            Flat current_cheapest_flat = result.get(district);
            if(current_cheapest_flat == null){
                result.put(district, flat);
            } else if (flat.room_count == required_room_count && flat.area >= min_area && flat.price < current_cheapest_flat.price){
                result.put(district, flat);
            }
        }
        return result;
    }
}
