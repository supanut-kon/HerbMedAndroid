package com.cnmi.herbmed_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.SumHolder> {

    private Context context;
    private ArrayList<SummaryModel> Sum;

    public CardAdapter(Context context, ArrayList<SummaryModel> sum) {
        this.context = context;
        this.Sum = sum;
    }

    @NonNull
    @Override
    public SumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new SumHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SumHolder holder, int position) {
        SummaryModel sum = Sum.get(position);
        holder.setDetails(sum ,position);
    }

    @Override
    public int getItemCount() {
        return Sum.size();
    }

    public class SumHolder extends RecyclerView.ViewHolder {
        private TextView textinfo, textHead;
        public SumHolder(@NonNull View itemView) {
            super(itemView);
            textHead = itemView.findViewById(R.id.txtHead);
            textinfo = itemView.findViewById(R.id.txtData);
        }

        void setDetails(SummaryModel sum, int pos){
            String head;
            switch (pos){
                case 0:
                    head = "Summary";
                    break;
                case 1:
                    head = "Clarification";
                    break;
                case 2:
                    head = "Documentation";
                    break;
                case 3:
                    head = "References";
                    break;
                case 4:
                    head = "Severity";
                    break;
                default:
                    head = "null";
            }

            String text = sum.getSumm();
            if(text == null){
                text = "ไม่พบข้อมูล";
            }
            textHead.setText(String.format(Locale.US, "%s", head));
            textinfo.setText(String.format(Locale.US, "%s", text));
        }
    }
}
