package com.sid.moviedatabase.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sid.moviedatabase.Model.RecyclerCrewModel;
import com.sid.moviedatabase.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.sid.moviedatabase.Constants.IMG_URL;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.CrewViewHolder> {

    Context context;
    List<RecyclerCrewModel> crewList;

    public CrewAdapter(Context context, List<RecyclerCrewModel> crewList) {
        this.context = context;
        this.crewList = crewList;
    }

    public class CrewViewHolder extends RecyclerView.ViewHolder{

        ImageView imgProf;
        TextView tvName,tvJob;

        public CrewViewHolder(@NonNull View view) {
            super(view);

            imgProf=view.findViewById(R.id.img_md_crew);
            tvName=view.findViewById(R.id.tv_md_crew);
            tvJob=view.findViewById(R.id.tv_md_job);
        }
    }

    @NonNull
    @Override
    public CrewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CrewViewHolder(LayoutInflater.from(context).inflate(R.layout.list_crew,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CrewViewHolder holder, int i) {

        RecyclerCrewModel crew = crewList.get(i);

        holder.tvName.setText(crew.getName());
        holder.tvJob.setText(crew.getJob());

        Picasso.get()
                .load(IMG_URL+crew.getProfUrl())
                .into(holder.imgProf);

    }

    @Override
    public int getItemCount() {
        return crewList.size();
    }
}
