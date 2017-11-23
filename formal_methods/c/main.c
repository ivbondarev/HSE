#include <stdio.h>

enum {
	OPENED,
	CLOSED
};

enum {
	LOCKED,
	UNLOCKED
};

enum {
	NOALARM,
	ALARM
};

struct key_door {
	int state;
	int lock;
	int alarm;
};

static void open_door(struct key_door *kd)
{
	if (kd->alarm)
		return;

	if (kd->lock == UNLOCKED)
		kd->state = OPENED;
}

int main(int argc, char **argv)
{
	struct key_door kd = {
		.state = OPENED,
		.lock = LOCKED,
		.alarm = NOALARM
	};

	return 0;
}
