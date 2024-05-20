package net.moita.snake.ui.task;

import net.moita.snake.ui.GameInstance;
import net.moita.snake.ui.Manager;

public final class RenderingTask implements Runnable {

    @Override
    public void run() {
        synchronized(Manager.getInstances()) {
            for(GameInstance instance : Manager.getInstances()) {
                if(!(instance.getPlayer().isOnline())) Manager.removeInstance(instance.getPlayer().getUniqueId());

                boolean isCollision = instance.getSnake().moveTo();
                instance.renderContent();

                if(isCollision) {
                    Manager.removeInstance(instance.getPlayer().getUniqueId());
                    instance.getPlayer().closeInventory();
                }
            }
        }
    }

}
