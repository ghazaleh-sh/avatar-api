package ir.co.sadad.avatarapi.common.logs;

public class BodyCaptureResponse
         {
//extends ServerHttpResponseDecorator
//    private final StringBuilder body = new StringBuilder();
//
//    public BodyCaptureResponse(ServerHttpResponse delegate) {
//        super(delegate);
//    }
//
//    @NotNull
//    @Override
//    public Mono<Void> writeWith(@NotNull Publisher<? extends DataBuffer> body) {
//        Flux<DataBuffer> buffer = Flux.from(body);
//        return super.writeWith(buffer.doOnNext(this::capture));
//    }
//
//    private void capture(DataBuffer buffer) {
//        this.body.append(StandardCharsets.UTF_8.decode(buffer.writableByteBuffers().next()));
//    }
//
//    public String getFullBody() {
//        return this.body.toString();
//    }

}