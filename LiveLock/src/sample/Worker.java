package sample;

public class Worker {
	
	private String name;
	private boolean active;
	
	public Worker(String name, boolean active) {
		super();
		this.name = name;
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public boolean isActive() {
		return active;
	}
	
	public synchronized void work(SharedResource resource, Worker otherWorker) {
		while(active) {
			if(resource.getOwner() != this) {
				try {
					wait(10);
				} catch (InterruptedException e) {
				}
				continue;
			}
			if(otherWorker.isActive()) {
				System.out.println(getName() + ": Handing over resource to " + otherWorker.getName());
				resource.setOwner(otherWorker);
				continue;
			}
			
			System.out.println(getName() + ": Working on the common resource");
			active = false;
			resource.setOwner(otherWorker);
		}
	}

}
