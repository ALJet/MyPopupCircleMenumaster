package indi.aljet.my_popupcirclemenu_master;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import indi.aljet.my_popupcirclemenu_master.popupcirclemenu.PopupCircleView;

public class ListActivity extends AppCompatActivity {
    ListView lv;

    ArrayList<bean> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listlayout);
        lv = (ListView)findViewById(R.id.lv);

        //模拟初始化数据--------------------------------------------------
        for (int i = 0; i < 20; i++)
            list.add(new bean());

        list.get(1).setLike(true);//勾选第二条的喜欢和收藏按钮
        list.get(1).setFavorite(true);

        list.get(2).setLike(true);//勾选第三条的喜欢按钮

        lv.setAdapter(new adapter());


    }


    private class adapter extends BaseAdapter{

        VH vh = null;

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final bean b = list.get(i);
            if(view == null){
                vh = new VH();
                view = LayoutInflater.from(ListActivity
                .this).inflate(R.layout.item,
                        null);
                vh.mPopupMenu = (PopupCircleView)
                        view.findViewById(R.id.PopupMenu);
                vh.mIv = (ImageView)view.findViewById(R.id
                .iv);
                view.setTag(vh);
            }else{
                vh = (VH) view.getTag();
            }
            vh.mIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ListActivity.this, "Image Click", Toast.LENGTH_SHORT).show();
                }
            });

            /**
             * 按钮被选中时的回调
             * */

            return view;
        }

        class VH{
            ImageView mIv;
            PopupCircleView mPopupMenu;
        }
    }

    private class bean{
        boolean like;
        boolean share;
        boolean favorite;

        public bean() {
        }

        public boolean isLike() {
            return like;
        }

        public void setLike(boolean like) {
            this.like = like;
        }

        public boolean isShare() {
            return share;
        }

        public void setShare(boolean share) {
            this.share = share;
        }

        public boolean isFavorite() {
            return favorite;
        }

        public void setFavorite(boolean favorite) {
            this.favorite = favorite;
        }
    }
}
