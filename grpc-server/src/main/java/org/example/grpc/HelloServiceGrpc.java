package org.example.grpc;

import hello.HelloServiceOuterClass;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloServiceGrpc extends hello.HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloServiceOuterClass.HelloRequest request,
                      StreamObserver<HelloServiceOuterClass.HelloResponse> responseObserver) {
        if (request.getName().isEmpty()) {
            responseObserver.onError(error());
        } else {
            responseObserver.onNext(next(request.getName()));
        }
        responseObserver.onCompleted();
    }

    private HelloServiceOuterClass.HelloResponse next(String name) {
        return HelloServiceOuterClass.HelloResponse.newBuilder()
                .setHello("Hello for " + name)
                .build();
    }

    private StatusRuntimeException error() {
        return Status.INVALID_ARGUMENT
                .withDescription("Name.")
                .asRuntimeException();
    }
}
