package com.example.gproject.meaning;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gproject.WordCard.Meaning;
import com.example.gproject.WordCard.Definition;
import com.example.gproject.WordCard.WordResult2;
import com.example.gproject.databinding.MeaningRecyclerRowBinding;

import java.util.List;

public class MeaningAdapter2 extends RecyclerView.Adapter<MeaningAdapter2.MeaningViewHolder> {

    private List<Meaning> meaningList;

    public MeaningAdapter2(List<Meaning> meaningList) {
        this.meaningList = meaningList;
    }

    public static class MeaningViewHolder extends RecyclerView.ViewHolder {
        public MeaningRecyclerRowBinding binding;
        public String definitionsText;

        public MeaningViewHolder(MeaningRecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(Meaning meaning) {
            binding.partOfSpeechTextview.setText(meaning.getPartOfSpeech());
            StringBuilder definitionsTextBuilder = new StringBuilder();
            List<Definition> definitionsList = meaning.getDefinitions();

            for (Definition definition : definitionsList) {
                definitionsTextBuilder.append((definitionsList.indexOf(definition) + 1))
                        .append(". ")
                        .append(definition.getDefinition())
                        .append("\n\n");
            }
            definitionsText = definitionsTextBuilder.toString();
            binding.definitionsTextview.setText(definitionsText);

            if (meaning.getSynonyms().isEmpty()) {
                binding.synonymsTitleTextview.setVisibility(View.GONE);
                binding.synonymsTextview.setVisibility(View.GONE);
            } else {
                binding.synonymsTitleTextview.setVisibility(View.VISIBLE);
                binding.synonymsTextview.setVisibility(View.VISIBLE);
                binding.synonymsTextview.setText(String.join(", ", meaning.getSynonyms()));
            }

            if (meaning.getAntonyms().isEmpty()) {
                binding.antonymsTitleTextview.setVisibility(View.GONE);
                binding.antonymsTextview.setVisibility(View.GONE);
            } else {
                binding.antonymsTitleTextview.setVisibility(View.VISIBLE);
                binding.antonymsTextview.setVisibility(View.VISIBLE);
                binding.antonymsTextview.setText(String.join(", ", meaning.getAntonyms()));
            }
        }
        public String getDefinitionsText() {
            return definitionsText;
        }
    }

    public void updateNewData(List<Meaning> newMeaningList) {
        meaningList = newMeaningList;
        notifyDataSetChanged();
    }

    @Override
    public MeaningViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MeaningRecyclerRowBinding binding = MeaningRecyclerRowBinding.inflate(layoutInflater, parent, false);
        return new MeaningViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MeaningViewHolder holder, int position) {
        holder.bind(meaningList.get(position));
    }

    @Override
    public int getItemCount() {
        return meaningList.size();
    }
}