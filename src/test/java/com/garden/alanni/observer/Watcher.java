package com.garden.alanni.observer;

import com.garden.alanni.designModel.watch.Observer;
import com.garden.alanni.designModel.watch.Subject;
import com.garden.alanni.designModel.watch.impl.ConcreteObserverA;
import com.garden.alanni.designModel.watch.impl.ConcreteObserverB;
import com.garden.alanni.designModel.watch.impl.ConcreteSubjectStr;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author 吴宇伦
 */
public class Watcher {
    @Test
    public void observersHandleEventsFromSubject() {
        // given
        Subject<String> subject = new ConcreteSubjectStr();
//        Observer<String> conObserA = Mockito.spy(new ConcreteObserverA());
//        Observer<String> conObserB = Mockito.spy(new ConcreteObserverB());
        Observer<String> conObserA = new ConcreteObserverA();
        Observer<String> conObserB = new ConcreteObserverB();

        // when
        subject.notifyObservers("No listeners");

        subject.registerObserver(conObserA);
        subject.notifyObservers("Message for A");

        subject.registerObserver(conObserB);
        subject.notifyObservers("Message for A & B");

        subject.unRegisterObserver(conObserA);
        subject.notifyObservers("Message for B");

        subject.unRegisterObserver(conObserB);
        subject.notifyObservers("No listeners");

//        // then
        Mockito.verify(conObserA, Mockito.times(1)).observe("Message for A");
        Mockito.verify(conObserA, Mockito.times(1)).observe("Message for A & B");
        Mockito.verifyNoMoreInteractions(conObserA);

        Mockito.verify(conObserB, Mockito.times(1)).observe("Message for A & B");
        Mockito.verify(conObserB, Mockito.times(1)).observe("Message for B");
        Mockito.verifyNoMoreInteractions(conObserB);


    }
}
