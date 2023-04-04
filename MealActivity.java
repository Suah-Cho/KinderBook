package com.example.parentsletterproject.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parentsletterproject.R;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MealActivity extends AppCompatActivity {

    TextView textView1, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        String url = "https://open.neis.go.kr/hub/mealServiceDietInfo?KEY=c0dadaab5ff14ca48e48aa850bb3652a&Type=xml&pIndex=1&pSize=10&ATPT_OFCDC_SC_CODE=J10&SD_SCHUL_CODE=7751096";
        MealActivity.OpenAPI dust = new MealActivity.OpenAPI(url);
        dust.execute();

        textView1 = (TextView) findViewById(R.id.to_text);
        textView2 = (TextView) findViewById(R.id.yester_text);
        textView3 = (TextView) findViewById(R.id.tomo_text);

        //System.out.println(list.get(0));
    }

    class OpenAPI extends AsyncTask<Void, Void, String> {

        private String url;
        private ArrayList<String> list;

        public OpenAPI(String url) {

            this.url = url;
        }

        @Override
        protected String doInBackground(Void... params) {

            // parsing할 url 지정(API 키 포함해서)

            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            try {
                dBuilder = dbFactoty.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            Document doc = null;
            try {
                doc = dBuilder.parse(url);
            } catch (IOException | SAXException e) {
                e.printStackTrace();
            }

            // root tag
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName()); // Root element: result

            // 파싱할 tag
            NodeList nList = doc.getElementsByTagName("row");

            list = new ArrayList<>();

            for(int temp = 0; temp < nList.getLength(); temp++){
                Node nNode = nList.item(temp);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){

                    Element eElement = (Element) nNode;

                    String data = getTagValue("MLSV_YMD", eElement);
                    String meal_menu = getTagValue("DDISH_NM", eElement);

                    // System.out.println("OPEN_API data : "+data);
                    //System.out.println("OPEN_API 급식 메뉴 : "+meal_menu);

                    String trim = "[<br/>*]";

                    String resultMealMenu = meal_menu.toString().replaceAll(trim, " ");

                    System.out.println(resultMealMenu);

                    list.add(resultMealMenu);

                    //Log.d("OPEN_API", "data  : " + getTagValue("MLSV_YMD", eElement));
                    //Log.d("OPEN_API", "급식 메뉴  : " + getTagValue("DDISH_NM", eElement));
                }   // for end
            }   // if end
            ;
            //System.out.println(list);

            return null;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            textView1.setText(list.get(0).toString());
            textView2.setText(list.get(1).toString());
            textView3.setText(list.get(2).toString());

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("meal", list.get(0).toString());


        }

        private String getTagValue(String tag, Element eElement) {
            NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
            Node nValue = (Node) nlList.item(0);
            if(nValue == null)
                return null;
            return nValue.getNodeValue();
        }

        public ArrayList<String> getList() {

            //doInBackground();
            return list;
        }

    }

}
