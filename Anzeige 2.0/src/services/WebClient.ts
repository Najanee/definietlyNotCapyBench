import { Topic } from "../domain/Topic";

async function getTopics() {
    const response = await fetch("http://localhost:8080/topics")
    const topics: Topic[] = await response.json();
    console.log(topics);
    return topics
  };

export { getTopics }
