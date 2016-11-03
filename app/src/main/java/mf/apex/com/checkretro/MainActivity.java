package mf.apex.com.checkretro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.orm.SugarContext;


import java.util.ArrayList;
import java.util.List;

import mf.apex.com.checkretro.API.MyInterface;
import mf.apex.com.checkretro.DB.Users;
import mf.apex.com.checkretro.DB.PostResponce;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    public String BaseUrl="http://abc.com/mylogin/"; //change this with your webservice base path
    Retrofit retrofit;
    ListView mList;
    ArrayList<Users> listdata;
    ArrayList <String> newlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(MainActivity.this);

        mList=(ListView)findViewById(R.id.list);
        newlist=new ArrayList<>();
        retrofit= new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BaseUrl)
                .build();

        DBbackup.copyDatabase(getApplicationContext());


        getdata();
    }

    public void getdata(){
        MyInterface myInterface=retrofit.create(MyInterface.class);
        Observable<PostResponce> obresponce=myInterface.getFiller();
        obresponce.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PostResponce>() {
                    @Override
                    public void onCompleted() {
                        displaylist();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PostResponce postResponce) {

                        List<Users> fl = postResponce.getPosts();


                        for (Users f : fl) {

                            List<Users> data = Users.findWithQuery(Users.class, "select * from Users");

                            if (data.size() > 0) { //check local db empty or not

                                for (int i=0;i<data.size();i++){

                                    newlist.add(data.get(i).getUname());
                                }


                            } else {
                                f.save();

                            }

                        }

                    }
                });

    }


    public void displaylist(){


       ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, android.R.id.text1, newlist);

        mList.setAdapter(adapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object ob=mList.getItemAtPosition(position);

                String value=ob.toString();


                Toast.makeText(MainActivity.this," "+value,Toast.LENGTH_LONG).show();
            }
        });

    }


}
