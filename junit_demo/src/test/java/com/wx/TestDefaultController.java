package com.wx;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDefaultController {
    private DefaultController controller;


    @Before
    public void instantiate() throws Exception {
        controller = new DefaultController();
    }

    private class SampleRequest implements Request {
        private static final String DEFAULT_NAME = "Test";
        private String name;

        public SampleRequest(String name) {
            this.name = name;
        }

        public SampleRequest() {
            this(DEFAULT_NAME);
        }

        @Override
        public String getName() {
            return name;
        }
    }

    private class SampleHandler implements RequestHandler {
        @Override
        public Response process(Request request) throws Exception {
            return new SampleResponse();
        }
    }

    private class SampleRequestHandler implements RequestHandler {
        @Override
        public Response process(Request request) throws Exception {
            return new SampleResponse();
        }
    }

    private class SampleExceptionHandler implements RequestHandler {
        @Override
        public Response process(Request request) throws Exception {
            throw new Exception("error processing request");
        }
    }

    private class SampleResponse implements Response {

    }

    @Test
    public void testAddHandler() {
        Request request = new SampleRequest();
        RequestHandler handler = new SampleRequestHandler();
        controller.addHandler(request, handler);
        RequestHandler handler2 = controller.getHandler(request);
        Assert.assertSame("Handler we set in controller should be the" +
                "same handler we get ", handler2, handler);
    }

    @Test
    public void testProcessRequest() {
        Request request = new SampleRequest();
        RequestHandler handler = new SampleRequestHandler();
        controller.addHandler(request, handler);
        Response response = controller.processRequest(request);
        Assert.assertNotNull("must not return a null response", response);
        Assert.assertEquals("Response should be of type SampleResponse", SampleResponse.class, response.getClass());
    }

    @Test
    public void testProcessRequestAnswersErrorResponse() {
        Request request = new SampleRequest("testError");
        RequestHandler exceptionHandler = new SampleExceptionHandler();
        controller.addHandler(request, exceptionHandler);
        Response response = controller.processRequest(request);
        Assert.assertNotNull("must not return a null response", response);
        Assert.assertEquals("Response should be of type ErrorResponse", ErrorResponse.class, response.getClass());
    }

    @Test(expected = RuntimeException.class)
    public void testGetHandlerNotDefined() {
        SampleRequest sampleRequest = new SampleRequest("testNotDefined");
        controller.getHandler(sampleRequest);
    }

    @Test(expected = RuntimeException.class)
    public void testAddRequestDuplicateName() {
        Request request = new SampleRequest();
        Request sampleRequest = new SampleRequest();
        SampleHandler sampleHandler = new SampleHandler();
        controller.addHandler(request, sampleHandler);
        controller.addHandler(sampleRequest, sampleHandler);
    }

    @Test(timeout = 1)
    public void testProcessMultipleRequestsTimeOut() {
        Request request;
        Response response;
        RequestHandler handler = new SampleHandler();
        for (int i = 0; i < 99999; i++) {
            request = new SampleRequest(String.valueOf(i));
            controller.addHandler(request, handler);
            response = controller.processRequest(request);
            Assert.assertNotNull(response);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Assert.assertEquals("Response should be of type ErrorResponse", ErrorResponse.class, response.getClass());
        }
    }
}
