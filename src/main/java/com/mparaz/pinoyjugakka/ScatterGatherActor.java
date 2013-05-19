package com.mparaz.pinoyjugakka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;

/**
 * Receive messages and scatter to the workers
 */
public class ScatterGatherActor extends UntypedActor {

    private final ActorRef workerRouter;

    public ScatterGatherActor(int numberOfWorkers) {
        workerRouter = getContext().actorOf(new Props(WorkerActor.class)
                .withRouter(new RoundRobinRouter(numberOfWorkers)), "workerRouter");

    }


    @Override
    public void onReceive(Object message) throws Exception {
        final ActorRef self = getSelf();
        if (message instanceof MyMessage) {
            // Initial. Do nothing
            final MyMessage myMessage = (MyMessage) message;

            int n = myMessage.getN();

            System.out.println("ScatterGatherActor " + self + ": onReceive message: " + n);

            if (n == -1) {
                // Shut down our hierarchy
                getContext().stop(self);
                return;
            }

            // Send to the worker
            workerRouter.tell(myMessage, self);

        } else if (message instanceof MyMessageResult) {
            // Result coming back from the worker

            final MyMessageResult myMessageResult = (MyMessageResult) message;

            System.out.println("ScatterGatherActor " + self + ": onReceive result: " + myMessageResult.getN());

        } else {
            unhandled(message);
        }
    }
}
