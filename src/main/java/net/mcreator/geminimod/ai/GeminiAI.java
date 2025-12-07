package net.mcreator.geminimod.ai;

import com.google.cloud.aiplatform.v1.EndpointName;
import com.google.cloud.aiplatform.v1.PredictionServiceClient;
import com.google.cloud.aiplatform.v1.PredictionServiceSettings;
import com.google.cloud.aiplatform.v1.PredictResponse;
import com.google.protobuf.Value;
import com.google.protobuf.util.JsonFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeminiAI {

    public static String getNextAction(String mobState) throws IOException {
        String projectId = System.getenv("GCP_PROJECT_ID");
        String endpointId = System.getenv("GCP_ENDPOINT_ID");
        String location = "us-central1";
        String publisher = "google";
        String model = "gemini-1.5-flash-001";

        if (projectId == null || endpointId == null) {
            System.err.println("GCP_PROJECT_ID and GCP_ENDPOINT_ID environment variables must be set.");
            return "wander"; // Default action if environment variables are not set
        }

        try (PredictionServiceClient predictionServiceClient = PredictionServiceClient.create()) {
            String endpoint = String.format("projects/%s/locations/%s/publishers/%s/models/%s",
                projectId, location, publisher, model);
            EndpointName endpointName = EndpointName.of(projectId, location, endpointId);

            String prompt = "You are a Minecraft mob. Based on the following state, what is your next action? " + mobState;
            String instance = "{\"prompt\": \"" + prompt + "\"}";
            Value.Builder instanceValue = Value.newBuilder();
            JsonFormat.parser().merge(instance, instanceValue);
            List<Value> instances = new ArrayList<>();
            instances.add(instanceValue.build());

            PredictResponse predictResponse = predictionServiceClient.predict(endpointName, instances, Value.newBuilder().build());
            return predictResponse.getPredictions(0).getStringValue();
        }
    }
}
