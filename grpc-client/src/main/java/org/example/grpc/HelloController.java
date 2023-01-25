package org.example.grpc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloServiceGrpcImpl serviceGrpc;

    public HelloController(HelloServiceGrpcImpl serviceGrpc) {
        this.serviceGrpc = serviceGrpc;
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getHello(@PathVariable String name) {
        return ResponseEntity.ok(serviceGrpc.hello(name));
    }
}
