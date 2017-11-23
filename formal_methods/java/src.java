class key_door {
	int state; /* 0 - opened, 1 - closed */
	int lock; /* 0 - locked, 1 - unlocked */
	int alarm; /* 0 - no alarm, 1 - alarm */

	void open() {
		/* Closed, locked, no alarm */
		if (state == 1 && lock == 0 && alarm == 0) {
			alarm = 1;
			state = 0;
		}
		/* Closed, unlocked, no alarm  */
		if (state == 1 && lock == 1 && alarm == 0)
			state = 0;
		/* Opened, unlocked, no alarm */
		if (state == 0 && lock == 1 && alarm == 0)
			return;
		/* Opened, locked, no alarm  */
		if (state == 0 && lock == 0 && alarm == 0)
			return;
		/* Alarm */
		if (alarm == 1)
			return;
	}

	void close() {
		/* Closed, locked, no alarm */
		if (state == 1 && lock == 0 && alarm == 0)
			return;
		/* Closed, unlocked, no alarm  */
		if (state == 1 && lock == 1 && alarm == 0)
			return;
		/* Opened, unlocked, no alarm */
		if (state == 0 && lock == 1 && alarm == 0)
			state = 1;
		/* Opened, locked, no alarm  */
		if (state == 0 && lock == 0 && alarm == 0)
			state = 1;
		/* Alarm */
		if (alarm == 1)
			return;
	}

	void insert_key() {
		/* Closed, locked, no alarm */
		if (state == 1 && lock == 0 && alarm == 0)
			lock = 1;
		/* Closed, unlocked, no alarm  */
		if (state == 1 && lock == 1 && alarm == 0)
			return;
		/* Opened, unlocked, no alarm */
		if (state == 0 && lock == 1 && alarm == 0)
			return;
		/* Opened, locked, no alarm  */
		if (state == 0 && lock == 0 && alarm == 0)
			lock = 1;
		/* Alarm */
		if (alarm == 1)
			return;
	}

	void take_key() {
		/* Closed, locked, no alarm */
		if (state == 1 && lock == 0 && alarm == 0)
			return;
		/* Closed, unlocked, no alarm  */
		if (state == 1 && lock == 1 && alarm == 0)
			lock = 0;
		/* Opened, unlocked, no alarm */
		if (state == 0 && lock == 1 && alarm == 0)
			lock = 0;
		/* Opened, locked, no alarm  */
		if (state == 0 && lock == 0 && alarm == 0)
			return;
		/* Alarm */
		if (alarm == 1)
			return;
	}

	public static void main(String[] args) {
		key_door kd = new key_door();
		// Closed
		kd.state = 1;
		// Locked
		kd.lock = 0;
		// No alarm
		kd.alarm = 0;

		kd.insert_key();
		kd.open();
		kd.take_key();
		kd.close();
		kd.close();

		System.out.println("State: " + kd.state
				   + " Lock: " + kd.lock
				   + " Alarm: " + kd.alarm);
	}
}
