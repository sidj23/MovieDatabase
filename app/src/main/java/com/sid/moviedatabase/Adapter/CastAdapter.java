package com.sid.moviedatabase.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sid.moviedatabase.Model.RecyclerCastModel;
import com.sid.moviedatabase.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.sid.moviedatabase.Constants.IMG_URL;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

    Context context;
    List<RecyclerCastModel> castList;

    public CastAdapter(Context context, List<RecyclerCastModel> castList) {
        this.context = context;
        this.castList = castList;
    }

    public class CastViewHolder extends RecyclerView.ViewHolder{

        ImageView imgProf;
        TextView tvName,tvChar;

        public CastViewHolder(@NonNull View view) {
            super(view);

            imgProf=view.findViewById(R.id.img_md_cast);
            tvChar=view.findViewById(R.id.tv_md_char);
            tvName=view.findViewById(R.id.tv_md_cast);
        }
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CastViewHolder(LayoutInflater.from(context).inflate(R.layout.list_cast,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int i) {

        RecyclerCastModel cast = castList.get(i);

        holder.tvChar.setText(cast.getCharacter());
        holder.tvName.setText(cast.getName());

        Picasso.get()
                .load(IMG_URL+cast.getProfUrl())
                .into(holder.imgProf);
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }
}
