package info.androidhive.speechtotext;


import android.os.AsyncTask;
import android.util.StringBuilderPrinter;

import com.google.gson.internal.LinkedTreeMap;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Abhishek on 5/13/17.
 */

public class ConversationWatsonApi extends AsyncTask<String, Void, String>{

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


    /*public ConversationWatsonApi()
    {
        service = new ConversationService(ConversationService.VERSION_DATE_2016_07_11);
        service.setUsernameAndPassword("857b0927-8ac0-45e2-9798-d644bc416db7", "LHIvH4epd8K3");

        MessageRequest newMessage = new MessageRequest.Builder().inputText("").build();
        MessageResponse response = service.message("c6b801e8-c630-418c-89d1-284b17ba2382", newMessage).execute();
    }*/

    public String sendToWatson(String textToWatson)
    {
        ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2017_02_03);
        service.setUsernameAndPassword("857b0927-8ac0-45e2-9798-d644bc416db7", "LHIvH4epd8K3");

        MessageRequest newMessage = new MessageRequest.Builder().inputText(textToWatson).build();
        MessageResponse response = service.message("c6b801e8-c630-418c-89d1-284b17ba2382", newMessage).execute();
        List<String> list = new ArrayList<String>();



        if(response.getOutput().get("text").toString().length()!=0)
        {
            //String conversationId = response.getContext().get("conversation_id").toString();

            MessageRequest firstMessage = new MessageRequest.Builder().inputText(textToWatson).context(response.getContext()).build();
            MessageResponse firstResponse = service.message("c6b801e8-c630-418c-89d1-284b17ba2382", firstMessage).execute();

            //System.out.println(firstResponse.getIntents().get(0).getIntent().toString());

            if(firstResponse.getOutput().get("text").toString().length()!=0)
            {
                list = (List<String>) firstResponse.getOutput().get("text");
                return list.get(0);
            }
        }

        return list.get(0);


    }

    @Override
    protected String doInBackground(String... params) {
        String res = sendToWatson(params[0]);
        return res;
    }
}
