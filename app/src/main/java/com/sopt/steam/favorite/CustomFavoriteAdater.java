package com.sopt.steam.favorite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;
import com.sopt.steam.R;
import com.sopt.steam.database.DbOpenHelper;

import java.sql.SQLException;
import java.util.List;



/**
 * Created by 즤 on 2016-07-02.
 */


public class CustomFavoriteAdater extends BaseAdapter {

    LayoutInflater mInflater;
    List<FavoriteSpaceData> arSrc;

    private Context context;
    private AQuery aq;
    private DbOpenHelper mDbOpenHelper;

    private KakaoLink mKakaoLink;
    private KakaoTalkLinkMessageBuilder mKakaoTalkLinkMessageBuilder;


    private View.OnClickListener sendEvent;

    //생성자
    public CustomFavoriteAdater(Context context, List<FavoriteSpaceData> arItem) {
        //인플레이트 준비를 합니다.
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arSrc = arItem;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arSrc.size();
    }

    @Override
    public FavoriteSpaceData getItem(int position) {
        // TODO Auto-generated method stub
        return arSrc.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        // TODO Auto-generated method stub

        if (convertView == null) {
            holder = new ViewHolder();

            convertView = mInflater.inflate(R.layout.listview_favorite, parent, false);

            holder.tv_img = (ImageView) convertView.findViewById(R.id.iv_imageview);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_info1 = (TextView) convertView.findViewById(R.id.tv_number);
            holder.tv_info2 = (TextView) convertView.findViewById(R.id.tv_option);
            holder.deleteMark = (ImageView) convertView.findViewById(R.id.deleteMark);
            holder.sendKakao = (ImageView) convertView.findViewById(R.id.sendKakao);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        aq = new AQuery(convertView);
        aq.id(holder.tv_img).image(arSrc.get(position).img);

        holder.tv_name.setText(arSrc.get(position).roomName);
        holder.tv_info1.setText(arSrc.get(position).info1);
        holder.tv_info2.setText(arSrc.get(position).info2);

        holder.deleteMark.setId(Integer.valueOf(arSrc.get(position).bookmarkId));
        holder.sendKakao.setId(Integer.valueOf(arSrc.get(position).bookmarkId));

        holder.deleteMark.setOnClickListener(deleteEvent);
//        holder.sendKakao.setOnClickListener(sendKaKaoEvent);

        return convertView;
    }


    private View.OnClickListener deleteEvent = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            // DB Create and Open
            mDbOpenHelper = new DbOpenHelper(context);
            try {
                mDbOpenHelper.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //리스트뷰 갱신
            Intent reload = new Intent(context ,FavoriteSpace.class);

            reload.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            reload.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            reload.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);


            for(int i = 0;i<arSrc.size();i++){
                if(arSrc.get(i).bookmarkId == String.valueOf(v.getId())){
                    arSrc.remove(i);
                    mDbOpenHelper.DbDelete(String.valueOf(v.getId()));
                    notifyDataSetChanged();
                    context.startActivity(reload);
//                    Log.i("Test",arSrc.get(i).bookmarkId + " " + String.valueOf(v.getId()));
                    break;
                }
            }

            Toast.makeText(context,"해당 게시글 북마크 해제",Toast.LENGTH_SHORT).show();



        }
    };

    private View.OnClickListener sendKaKaoEvent = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {

            Toast.makeText(context,"카카오톡으로 이동!",Toast.LENGTH_SHORT).show();
            int index = 0;
            for(int i = 0;i<arSrc.size();i++) {
                if (arSrc.get(i).roomId == String.valueOf(v.getId())) {
                    index = i;
                    break;
                }
            }

            String url = String.valueOf(arSrc.get(index).img);

            try {
                mKakaoLink = KakaoLink.getKakaoLink(context);
                mKakaoTalkLinkMessageBuilder = mKakaoLink.createKakaoTalkLinkMessageBuilder();
            } catch (KakaoParameterException e) {
                e.printStackTrace();
            }

            try {
                mKakaoTalkLinkMessageBuilder.addImage(url, 128, 128);
                mKakaoTalkLinkMessageBuilder.addText(arSrc.get(index).roomName + " 여기서 모이자!");
//                mKakaoTalkLinkMessageBuilder.addAppButton("테스트 앱 열기", new AppActionBuilder()
//                        .setAndroidExecuteURLParam("target=main")
//                        .setIOSExecuteURLParam("target=main", AppActionBuilder.DEVICE_TYPE.PHONE).build());



                //액티비티가 아닌곳에서 호출하기때문에 옵션을 줘야하는데....흠..
//                Intent intent = new Intent(context, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//
//                context.addF
                mKakaoLink.sendMessage(mKakaoTalkLinkMessageBuilder,context);

            } catch (KakaoParameterException e) {
                e.printStackTrace();
            }

//            Log.i("Test",String.valueOf(v.getId()));
        }
    };

    private class ViewHolder{
        public ImageView tv_img;
        public TextView tv_name;
        public TextView tv_info1;
        public TextView tv_info2;
        public ImageView deleteMark;
        public ImageView sendKakao;
    }


}


