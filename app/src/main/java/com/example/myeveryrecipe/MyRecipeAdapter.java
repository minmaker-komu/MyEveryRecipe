package com.example.myeveryrecipe;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecipeAdapter extends RecyclerView.Adapter<MyRecipeAdapter.ItemViewHolder>{

    private ArrayList<MyRecipeData> mData;
    private Context mContext;
    MyRecipeAdapter(ArrayList<MyRecipeData> list) {

        mData = list ;
        //mContext = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myrecipe_recycler_item, parent, false);
        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return mData.size();
    }


    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        private ImageView recipe_image ;
        private TextView recipe_name ;
        private TextView recipe_food ;
        private ImageView select_btn;



        ItemViewHolder(View itemView) {
            super(itemView);

            // 길게 누르면 삭제하기
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAdapterPosition();
                    androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setTitle("레시피 삭제하기")
                            .setMessage(mData.get(position).getRecipe_name() + "을(를) 삭제하시겠습니까?")
                            .setPositiveButton("삭제하기", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    mData.remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position,mData.size());
                                }
                            })
                            .setNeutralButton("취소", null)
                            .show();
                    return false;
                }
            });
            //itemView.setOnCreateContextMenuListener(MyRecipeAdapter.ItemViewHolder.this);


            // 뷰 객체에 대한 참조. (hold strong reference)
            recipe_image = itemView.findViewById(R.id.recipe_image) ;
            recipe_name = itemView.findViewById(R.id.recipe_name) ;
            recipe_food = itemView.findViewById(R.id.food_name) ;
            select_btn = itemView.findViewById(R.id.select_btn);

            select_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("%%%1212");
                }
            });

        }

        //context 메뉴

        @Override
        public void onCreateContextMenu(@NonNull ContextMenu menu,
                                        View v,
                                        ContextMenu.ContextMenuInfo menuInfo)
        {
            MenuItem Edit = menu.add(Menu.NONE,1001,1,"편집");
            MenuItem Delete = menu.add(Menu.NONE,1002,2,"삭제");
            if(v==select_btn){
                Edit.setOnMenuItemClickListener(onEditMenu);
                Delete.setOnMenuItemClickListener(onEditMenu);
            }


        }
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    // 나의 레시피 수정
                    case 1:
                        System.out.println("11111111111");
                        break;
                    case 2:
                        mData.remove(getAdapterPosition());
                        System.out.println("^^DDD^^^");
                        notifyDataSetChanged();
                        break;
                }
                return false;
            }
        };

        void onBind(MyRecipeData data) {

            recipe_image.setImageResource(data.getRecipe_image());
            recipe_name.setText(data.getRecipe_name());
            recipe_food.setText(data.getRecipe_food());
        }
    }

}
