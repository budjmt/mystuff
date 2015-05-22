const int knob0 = A3;
const int knob1 = A4;
const int knob2 = A5;

const int speaker = 9;
const int led = 10;

bool win;
bool roundOver;

void setup() {
  pinMode(knob0, INPUT);
  pinMode(knob1, INPUT);
  pinMode(knob2, INPUT);
  pinMode(speaker, OUTPUT);
  pinMode(led, OUTPUT);

  Serial.begin(57600);
  analogReference(DEFAULT);
  contact();
}

void contact() {
  while (!Serial.available()) {
    Serial.println('A');
    delay(300);
  }
}

void checkWin() {
  win = (Serial.read() == '1') ? true : false;
  if (win) {
    digitalWrite(led, HIGH);
    tone(speaker, 200);
    delay(500);
    noTone(speaker);
    digitalWrite(led, LOW);
  }
  else {
    tone(speaker, 100);
    delay(500);
    noTone(speaker);
  }
  Serial.print(true);
  Serial.println();
}

void sendValues() {
  float knobs[] = { analogRead(knob0)
                    , analogRead(knob1)
                    , analogRead(knob2)
                  };
  for (int i = 0; i < 2; i++) {
    Serial.print(knobs[i]);
    Serial.print(",");
  }
  Serial.print(knobs[2]);
  Serial.println();
}

void loop() {
  if (Serial.available())
    checkWin();
  else
    sendValues();
}
