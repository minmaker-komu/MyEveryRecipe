package com.example.myeveryrecipe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerRecipeAdapter extends RecyclerView.Adapter<RecyclerRecipeAdapter.ViewHolder> {
    //private Context mContext;
    private ArrayList<MyRecipeData> mData;

    RecyclerRecipeAdapter(ArrayList<MyRecipeData> list) {

        mData = list ;
        //mContext = context;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.other_recipe_recycler_item, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MyRecipeData item = mData.get(position);

        holder.recipe_image.setImageResource(item.getRecipe_image());
        holder.recipe_title.setText(item.getRecipe_name());
        holder.recipe_food.setText(item.getRecipe_food());
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recipe_image;
        TextView recipe_title;
        TextView recipe_food;

        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            recipe_image = itemView.findViewById(R.id.recipe_image) ;
            recipe_title = itemView.findViewById(R.id.recipe_name) ;
            recipe_food = itemView.findViewById(R.id.food_name);

            // 레시피 읽기
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    MyRecipeData recipeData = mData.get(position);
                    Intent intent = new Intent(view.getContext(),ReadRecipe.class);
                    intent.putExtra("title", recipeData.getRecipe_name());
                    intent.putExtra("food",recipeData.getRecipe_food());
                    intent.putExtra("img2",recipeData.getRecipe_image());
                    intent.putExtra("need",recipeData.getRecipe_need());
                    intent.putExtra("recipe",recipeData.getRecipe_context());
                    intent.putExtra("position", position);

                    view.getContext().startActivity(intent);

                    /*Intent intent = new Intent(view.getContext(),ReadRecipe.class);
                    intent.putExtra("title", recipeData.getRecipe_name());
                    intent.putExtra("position", position);
                    intent.putExtra("img1",recipeData.getRecipe_image());
                    view.getContext().startActivity(intent);
                    Toast.makeText(view.getContext(), "클릭 되었습니다.", Toast.LENGTH_SHORT).show();*/
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAdapterPosition();
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setTitle("스크랩하기")
                            .setMessage(mData.get(position).getRecipe_name() + "을(를) 스크랩하시겠습니까?")
                            .setPositiveButton("스크랩하기", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // 스크랩
                                    System.out.println("###스크랩###");
                                    Toast.makeText(itemView.getContext(),"스크랩이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNeutralButton("취소", null)
                            .show();
                    return false;
                }
            });

        }
    }

}
