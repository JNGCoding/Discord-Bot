# Discord Bot

ValoraandBot is a Discord bot built using [JDA](https://github.com/DV8FromTheWorld/JDA) and [LavaPlayer](https://github.com/sedmelluq/lavaplayer). It features slash commands, message-based commands, Google Gemini API integration, and more.

## Features

- Slash commands (`/introduction`, `/name`, `/joke`, `/help`)
- Message-based commands (e.g., `botCommand/ask`, `botCommand/join`, `botCommand/leave`)
- Integration with Google Gemini API for AI-powered responses
- Audio features via LavaPlayer
- Event logging and colored console output

## Getting Started

### Prerequisites

- Java 20 or higher
- Maven

### Setup

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/ValoraandBot.git
    cd ValoraandBot
    ```

2. Fill in your credentials in [`Assets/ApplicationIDs.txt`](Assets/ApplicationIDs.txt):
    ```
    APPLICATION ID=your_application_id
    PUBLIC KEY=your_public_key
    TOKEN=your_discord_token
    GEMINI TOKEN=your_gemini_api_key
    GEMINI ANSWER LENGTH=your_max_token_length
    VERSION=1.00
    TESTING=TRUE
    ```

3. Build the project:
    ```sh
    mvn package
    ```

4. Run the bot:
    ```sh
    java -jar target/ValoraandBot-1.0-SNAPSHOT.jar
    ```

## License

MIT License

Copyright (c) 2024 [Your Name]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
