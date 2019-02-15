package com.example.vulcan.studio_yue.tablayout;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.xlistviewlibrary.view.XListView;
import com.example.vulcan.studio_yue.R;
import com.example.vulcan.studio_yue.adapter.MAdapter;
import com.example.vulcan.studio_yue.bean.PerBean;
import com.example.vulcan.studio_yue.dao.UserDao;
import com.example.vulcan.studio_yue.utils.NetUtils;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;


/***
 * 功能页面
 */
public class Aafragment extends Fragment {

    String urlstring = "http://result.eolinker.com/iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=one&";
    int page;
    private XListView xlistview;
    private List<PerBean.DataBean> list = new ArrayList<PerBean.DataBean>();
    private MAdapter adapter;
    private UserDao dao;
    private Banner banner;
    private List<String> list_path = new ArrayList<String>();
    private List<String> list_title = new ArrayList<String>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment11, container, false);
        xlistview = view.findViewById(R.id.xlistview);
        banner = view.findViewById(R.id.banner);

        iniTu();
        //支持上下拉刷新
        xlistview.setPullLoadEnable(true);
        dao = new UserDao(getActivity());
        //适配器刷新
        adapter = new MAdapter(getActivity(), list);
        xlistview.setAdapter(adapter);

        iniData(page);

        //xlistview监听
        xlistview.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                list.clear();
                page=0;
                iniData(page);
            }

            @Override
            public void onLoadMore() {
                page++;
                iniData(page);
            }
        });

        return view;
    }

    private void iniData(int page) {
        String s = urlstring + page;
        if (NetUtils.iscoon(getActivity())) {
            new Async().execute(s);
        }else {
            Toast.makeText(getActivity(),"网络已断开",Toast.LENGTH_SHORT).show();
            //数据库查询
            list = dao.query();
            adapter = new MAdapter(getActivity(), list);
            xlistview.setAdapter(adapter);
        }
    }

    class Async extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return NetUtils.getJson(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            PerBean fromJson = gson.fromJson(s, PerBean.class);
            List<PerBean.DataBean> data = fromJson.getData();
            list.addAll(data);

            //数据库添加
            List<PerBean.DataBean> query = dao.query();
            if (query.size()<50){
                for (int i=0;i<list.size();i++){
                    String ivs = list.get(i).getImage_url();
                    String tes = list.get(i).getTitle();
                    String tee = list.get(i).getContent();
                    dao.add(ivs,tes,tee);
                }
            }

            adapter.notifyDataSetChanged();
            shuxin();
        }

        private void shuxin() {
            xlistview.setRefreshTime("刚刚");
            xlistview.stopRefresh();
            xlistview.stopLoadMore();
        }
    }


    /**
     * banner图
     */
    private void iniTu(){

        list_path.add("http://image.wufazhuce.com/FuPgOcbGDD9__fyuCdPBXb5pbA1C");
        list_path.add("http://image.wufazhuce.com/Fu6o0fqKHsI_TjdpPX3N2Kc99vNP");
        list_path.add("http://image.wufazhuce.com/FmDRnQ1XhReHRHB4jYqAPSx8htsP");
        list_path.add("http://image.wufazhuce.com/FvVmWbqlle7jlUCTeozoval9NyBH");

        list_title.add("写小说。短篇小说集《小镇忧郁青年的十八种死法》。");
        list_title.add("写小说。短篇小说集《小镇忧郁青年的十八种死法》。");
        list_title.add("写小说。短篇小说集《小镇忧郁青年的十八种死法》。");
        list_title.add("写小说。短篇小说集《小镇忧郁青年的十八种死法》。");


        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new Imager());
        banner.setImages(list_path);
        banner.setBannerAnimation(Transformer.RotateDown);
        banner.setBannerTitles(list_title);
        banner.setDelayTime(1500);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();

    }


    private class Imager implements ImageLoaderInterface {

        @Override
        public void displayImage(Context context, Object path, View imageView) {
            Glide.with(context).load((String)path).into((ImageView)imageView);
        }

        @Override
        public View createImageView(Context context) {
            return null;
        }
    }



}
