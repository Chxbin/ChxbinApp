package com.example.chxbinapp.View;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chxbinapp.Model.AllSport;
import com.example.chxbinapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ListContentFragHome extends Fragment {
    List<AllSport> dataFromA;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_home, container, false);
        Bundle dataBundle = getArguments();

        Type type = new TypeToken<List<AllSport>>() {
        }.getType();
        dataFromA = new Gson().fromJson((dataBundle.getString("key")), type);

        RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);

        ContentAdapter adapter = new ContentAdapter(getContext(), dataFromA);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        // Set padding for Tiles (not needed for Cards/Lists!)
        int tilePadding = getResources().getDimensionPixelSize(R.dimen.fab_margin);
        recyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return view;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }

    }

        public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
            Context context;
            List<AllSport> datafromA;


            public ContentAdapter(Context context, List<AllSport> input) {
                this.datafromA = input;
                Resources resources = context.getResources();
            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(
                        parent.getContext());
                View v =
                        inflater.inflate(R.layout.row_layout, parent, false);

                ViewHolder vh = new ViewHolder(v);
                return vh;            }

            @Override
            public void onBindViewHolder(ViewHolder holder, final int position) {
                final AllSport currentAll = datafromA.get(position);
                final String name = currentAll.getStrSport();

                final int obs = currentAll.getIdSport();
                holder.txtHeader.setText(name);
                holder.txtFooter.setText("Id: " + obs);
            }

            @Override
            public int getItemCount() {
                return datafromA.size();
            }
        }
    }

