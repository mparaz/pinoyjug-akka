package com.mparaz.pinoyjugakka;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

/**
 * Worker
 */
public class WorkerActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        final ActorRef self = getSelf();

        if (message instanceof MyMessage) {
            final MyMessage myMessage =  (MyMessage) message;

            System.out.println("WorkerActor " + self + ": onReceive message: " + myMessage.getN());

            // Process and return
            // TODO Perform expensive computation here
            final MyMessageResult newMyMessageResult = new MyMessageResult(myMessage.getN() * 2);

            // No reply expected
            getSender().tell(newMyMessageResult, null);
        } else {
            unhandled(message);
        }
    }
}
