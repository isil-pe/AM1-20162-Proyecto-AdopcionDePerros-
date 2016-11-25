package com.example.dvasq.adopciondeperros.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dvasq.adopciondeperros.R;
import com.example.dvasq.adopciondeperros.model.PerroEntity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alumno-J on 24/11/2016.
 */
public class PerroAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<PerroEntity> data;
    private List<PerroEntity> originalData;
    private Filter petFilter;

    public PerroAdapter(Context context, List<PerroEntity> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(this.context).
                    inflate(R.layout.row_pet,parent,false);
        } else {
            view = convertView;
        }
        TextView tviPerroName = (TextView)view.findViewById(R.id.txvNameOfDog);
        TextView tviPerroRace = (TextView)view.findViewById(R.id.txvRaceOfDog);
        TextView tviPerroAge = (TextView)view.findViewById(R.id.txvAgeOfDog);

        final PerroEntity perroEntity= data.get(position);

        String name = "Nombre: " + perroEntity.getName();
        String race = "Raza: " + perroEntity.getRace();
        String edad = "Edad: "+perroEntity.getAge() ;
        tviPerroName.setText(name);
        tviPerroRace.setText(race);
        tviPerroAge.setText(edad);

        return view;
    }

    public void resetData() {
        originalData = data;
    }

    @Override
    public Filter getFilter() {
        if (petFilter == null)
            petFilter = new PetFilter();
        return petFilter;
    }

    private class PetFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list
                results.values = data;
                results.count = data.size();
            }
            else {
                // We perform filtering operation
                List<PerroEntity> nDogList = new ArrayList<PerroEntity>();

                for (PerroEntity perroEntity : originalData) {
                    if (perroEntity.getName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        nDogList.add(perroEntity);
                }

                results.values = nDogList;
                results.count = nDogList.size();

            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            // Now we have to inform the adapter about the new list filtered
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                originalData = (List<PerroEntity>) results.values;
                notifyDataSetChanged();
            }

        }

    }

    static class ViewHolder
    {
        TextView tviPerroName;
        TextView tviPerroRace;
        TextView tviPerroAge;
    }
}
