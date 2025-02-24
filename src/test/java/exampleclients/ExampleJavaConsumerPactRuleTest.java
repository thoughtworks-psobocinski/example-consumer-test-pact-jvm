package exampleclients;

import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.core.model.RequestResponsePact;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExampleJavaConsumerPactRuleTest {

    @Rule
    public PactProviderRule provider = new PactProviderRule("test_provider", "localhost", 8080, this);

    @Pact(provider="test_provider", consumer="test_consumer")
    public RequestResponsePact createFragment(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("testreqheader", "testreqheadervalue");

        return builder
            .given("test state")
            .uponReceiving("ExampleJavaConsumerPactRuleTest test interaction")
                .path("/")
                .method("GET")
                .headers(headers)
            .willRespondWith()
                .status(200)
                .headers(headers)
                .body("{\"responsetest\": true, \"name\": \"harry\"}")
            .given("test state")
            .uponReceiving("ExampleJavaConsumerPactRuleTest second test interaction")
                .method("OPTIONS")
                .headers(headers)
                .path("/second")
                .body("")
            .willRespondWith()
                .status(200)
                .headers(headers)
                .body("")
            .toPact();
    }

    @Test
    @PactVerification("test_provider")
    public void runTest() throws IOException {
        String providerUrl = provider.getUrl();
        System.out.println("Provider URL: " + providerUrl);

        assertNotNull("Provider URL is null! Aborting test.", providerUrl);
        assertEquals(new ConsumerClient(provider.getUrl()).options("/second"), 200);

        Map expectedResponse = new HashMap();
        expectedResponse.put("responsetest", true);
        expectedResponse.put("name", "harry");

        assertEquals(new ConsumerClient(provider.getUrl()).getAsMap("/", ""), expectedResponse);
    }
}