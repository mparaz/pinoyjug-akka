package com.mparaz.pinoyjugakka;

import akka.actor.*;

/**
 * Just launch.
 */
public class Main {

    private static final int NUMBER_OF_WORKERS = 10;

    private void go() {
        final ActorSystem actorSystem = ActorSystem.create("myActorSystem");

        // .class form will not work due to constructor - need a creator
//        final ActorRef scatterGatherActorRef = actorSystem.actorOf(new Props(ScatterGatherActor.class), "scatterActor");

        final ActorRef scatterGatherActorRef = actorSystem.actorOf(new Props(new UntypedActorFactory() {
            @Override
            public Actor create() throws Exception {
                return new ScatterGatherActor(NUMBER_OF_WORKERS);
            }
        }), "scatterGatherActor");

        // Send one
        scatterGatherActorRef.tell(new MyMessage(1), null);


        // If we send the shutdown message...
//      scatterGatherActorRef.tell(new MyMessage(-1), null);

        // Another
        scatterGatherActorRef.tell(new MyMessage(2), null);
    }

    public static void main(String[] args) {
        new Main().go();
    }
}
