# 🚀 QBar
**QBar** is a lightweight Spring Boot service for generating **QR codes** and **barcodes**.  
It supports **Redis caching** to improve performance for repeated requests.  
The service exposes a REST API that returns **PNG images**.
---
## ✨ Features
- Generate **QR codes** and **CODE 128 barcodes**
- Configurable **width** and **height**
- **Redis caching** to speed up repeated requests
- Ready to run in **Docker**
- Configurable via Spring profiles (`application.properties` and `application-docker.properties`)
---
## 🛠️ Technologies Used
- **Java 21**
- **Spring Boot 3.5**
- **Spring Cache + Redis**
- **ZXing** (QR and barcode generation)
- **Docker / Docker Compose**
---
## 🏁 Getting Started
### Prerequisites
- Docker & Docker Compose
- Java 21 (optional if running outside Docker)
- Maven (optional if running outside Docker)
---
### 🐳 Run with Docker
```bash
# Build and start services
docker-compose up --build
```
- QBar service will be available at: http://localhost:8081
- Redis is available internally at redis:6379

## 📬 API Endpoint

**Generate QR / Barcode**

### POST `/api/codes/generate`
```
Content-Type: application/json
Accept: image/png
```

### 📝 Request JSON

```json
{
  "text": "HelloWorld",
  "type": "QR",
  "width": 300,
  "height": 300
}
```

- text ✏️: The data you want to encode.
- type 🔢: "QR" for QR Code, "CODE_128" for Code 128 barcode. 
- width 📏: Width of the image in pixels. 
- height 📐: Height of the image in pixels.

### 🖼️ Response
```
Content-Type: image/png
Body: PNG image of the generated QR code or barcode.
```
### Example with cURL:

```bash
curl -X POST http://localhost:8081/api/codes/generate \
     -H "Content-Type: application/json" \
     -H "Accept: image/png" \
     -d '{"text":"HelloWorld","type":"QR","width":300,"height":300}' \
     --output qr.png
```
The generated QR code image will be saved as qr.png ✅

### 🔢 Example for Code 128 Barcode

```json
{
  "text": "1234567890",
  "type": "CODE_128",
  "width": 400,
  "height": 150
}
```
Saves the generated barcode as barcode.png using the same cURL command with updated JSON.

---
## 🛣️ Roadmap

- [ ] Support additional barcode formats (e.g., EAN, UPC)
- [ ] Add custom colors and logo overlays for QR codes 🎨 
- [ ] Implement rate limiting to avoid abuse 🚦 
- [ ] Add API key authentication for secure access 🔑 
- [ ] Provide online frontend for quick QR/barcode generation 🌐 
- [ ] Enable dynamic TTL for cached codes ⏳ 
- [ ] Add unit & integration tests for all endpoints ✅