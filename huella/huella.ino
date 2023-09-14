#include <Adafruit_Fingerprint.h>
#include <SoftwareSerial.h>

SoftwareSerial mySerial(2, 3);
Adafruit_Fingerprint finger = Adafruit_Fingerprint(&mySerial);

int id = 0;
boolean comandoRecibido = false;
char comando;

void setup() {
  Serial.begin(9600);
  mySerial.begin(57600);
  finger.verifyPassword();
}

void leerComando() {
  // Leer el comando enviado desde java
  if (Serial.available()) {
    String receivedData = Serial.readString();
    comando = receivedData.charAt(0); // Obtener el primer carácter del string

    id = atoi(receivedData.substring(1, receivedData.length()).c_str());

    comandoRecibido = true; // Confirma que ha recibido el comando desde java
  }
}

void ejecutarComando() {
    if(comandoRecibido) { // Una vez procesado el comando se procede con la lectura de la huella
    // registrar, verificar, eliminar
    switch(comando) {
     case 'r':
      while (!getFingerprintEnroll() && comandoRecibido);
      comandoRecibido = false;
     break;
     case 'v':
       while(!verifyFingerprint() && comandoRecibido);
       comandoRecibido = false;
     break;
     case 'e':
       deleteFingerprint();
       comandoRecibido = false;
     break;
     default :
      Serial.println("c");
      comandoRecibido = false;
     break;
    }

  }
}

void loop() {
  leerComando();
  ejecutarComando();  
}

uint8_t verifyFingerprint() {
  uint8_t p = finger.getImage();
  
  if (p != FINGERPRINT_OK) {
    Serial.print("e"); // Esperando...
    leerComando();
    if(comando == 't') {
      Serial.println("c");
      return 1;
    }
    return 0;
  }

  p = finger.image2Tz();
  if (p != FINGERPRINT_OK) {
    Serial.print("f"); // Error
    return 0;
  }

  p = finger.fingerFastSearch();
  if (p == FINGERPRINT_OK) {
    Serial.print(finger.fingerID); // Huella encontrada, retorna el ID
    return 1;
  } else if (p == FINGERPRINT_NOTFOUND) {
    Serial.print("n"); // No se encontró la huella
    return 1;
  } else {
    Serial.print("f"); // Ocurrió un error
    return 0;
  }
}


// Enrolar dedo
uint8_t getFingerprintEnroll() {
  int p = -1;
    // Serial.print("Waiting for valid finger to enroll as #");
    // Serial.println(id);
    while (p != FINGERPRINT_OK) {
      p = finger.getImage();
      switch (p) {
        case FINGERPRINT_OK:
          // Serial.println("Image taken");
          break;
        case FINGERPRINT_NOFINGER:
          Serial.println("e"); // Waiting finger
          leerComando();
          if(comando == 't') {
            Serial.println("c"); // pára la ejecución del lector
            return 1;
          }
          break;
        case FINGERPRINT_PACKETRECIEVEERR:
          Serial.println("f");
          break;
        case FINGERPRINT_IMAGEFAIL:
          Serial.println("f");
          break;
        default:
          Serial.println("f");
          break;
      }
    }
  
    // OK success!
  
    p = finger.image2Tz(1);
    switch (p) {
      case FINGERPRINT_OK:
        // Serial.println("Image converted");
        break;
      case FINGERPRINT_IMAGEMESS:
        Serial.println("f");
        return p;
      case FINGERPRINT_PACKETRECIEVEERR:
        Serial.println("f");
        return p;
      case FINGERPRINT_FEATUREFAIL:
        Serial.println("f");
        return p;
      case FINGERPRINT_INVALIDIMAGE:
        Serial.println("f");
        return p;
      default:
        Serial.println("f");
        return p;
    }
    
    Serial.println("o"); // Remove finger
    delay(2000);
    p = 0;
    while (p != FINGERPRINT_NOFINGER) {
      p = finger.getImage();
    }
    // Serial.print("ID ");
    // Serial.println(id);
    p = -1;
    // Serial.println("Place same finger again");
    while (p != FINGERPRINT_OK) {
      p = finger.getImage();
      switch (p) {
        case FINGERPRINT_OK:
          // Serial.println("Image taken");
          break;
        case FINGERPRINT_NOFINGER:
          Serial.print("e");
          leerComando();
          if(comando == 't') {
            Serial.println("c"); // pára la ejecución del lector
            return 1;
          }
          break;
        case FINGERPRINT_PACKETRECIEVEERR:
          Serial.println("f");
          break;
        case FINGERPRINT_IMAGEFAIL:
          Serial.println("f");
          break;
        default:
          Serial.println("f");
          break;
      }
    }
  
    // OK success!
  
    p = finger.image2Tz(2);
    switch (p) {
      case FINGERPRINT_OK:
        // Serial.println("Image converted");
        break;
      case FINGERPRINT_IMAGEMESS:
        Serial.println("f");
        return p;
      case FINGERPRINT_PACKETRECIEVEERR:
        Serial.println("f");
        return p;
      case FINGERPRINT_FEATUREFAIL:
        Serial.println("f");
        return p;
      case FINGERPRINT_INVALIDIMAGE:
        Serial.println("f");
        return p;
      default:
        Serial.println("f");
        return p;
    }
  
    // OK converted!
    // Serial.print("Creating model for #");
    // Serial.println('a'); // Confirmar que se capturó el ID
    // Serial.println(id);
  
    p = finger.createModel();
    if (p == FINGERPRINT_OK) {
      // Serial.println("Prints matched!");
    } else if (p == FINGERPRINT_PACKETRECIEVEERR) {
      Serial.println("f");
      return p;
    } else if (p == FINGERPRINT_ENROLLMISMATCH) {
      Serial.println("f");
      return p;
    } else {
      Serial.println("f");
      return p;
    }
  
    // Serial.print("ID ");
    // Serial.println(id);
    p = finger.storeModel(id);
    if (p == FINGERPRINT_OK) {
      Serial.println("o");
    } else if (p == FINGERPRINT_PACKETRECIEVEERR) {
      Serial.println("f");
      return p;
    } else if (p == FINGERPRINT_BADLOCATION) {
      Serial.println("f");
      return p;
    } else if (p == FINGERPRINT_FLASHERR) {
      Serial.println("f");
      return p;
    } else {
      Serial.println("f");
      return p;
    }
    
    return true;
  }

  // Eliminar huella
  uint8_t deleteFingerprint() {
  uint8_t p = -1;

  p = finger.deleteModel(id);

  if (p == FINGERPRINT_OK) {
    Serial.println("Deleted!");
  } else if (p == FINGERPRINT_PACKETRECIEVEERR) {
    Serial.println("Communication error");
  } else if (p == FINGERPRINT_BADLOCATION) {
    Serial.println("Could not delete in that location");
  } else if (p == FINGERPRINT_FLASHERR) {
    Serial.println("Error writing to flash");
  } else {
    Serial.print("Unknown error: 0x"); Serial.println(p, HEX);
  }

  return p;
}
  
