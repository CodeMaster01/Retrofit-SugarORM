# Retrofit SugarORM

Retrofit is a powerful and safe REST client for Android developments.When it's combine with SugarORM and RxAndroid prety simple and easy for developments.

#Setup
Make sure require Internet permissions in your AndroidManifest.xml

    <uses-permission android:name="android.permission.INTERNET"/>
    
initialize SugarORM in AndroidManifest.xml

    <application
        android:name="com.orm.SugarApp"
        android:allowBackup="true"
        android:icon="@drawable/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/AppTheme" >
        
        ..........
        </application>


Add the following to your app/build.gradle file:

    dependencies {
        compile 'com.google.code.gson:gson:2.6.2'
        compile 'com.squareup.retrofit2:retrofit:2.1.0'
        compile 'com.squareup.retrofit2:converter-gson:2.1.0' 
        compile 'com.github.satyan:sugar:1.4'
        compile 'io.reactivex:rxandroid:1.0.1'
        compile 'io.reactivex:rxjava:1.0.12'

    }

#Initialize Retrofit in Activity class

      retrofit= new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BaseUrl)
                .build();
                
