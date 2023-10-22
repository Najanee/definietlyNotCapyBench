import { PostDto } from "../components/dto/PostDto";
import { Topic } from "../domain/Topic";

async function getTopics() : Promise<Topic[]> {
  const response = await fetch("http://localhost:8080/topics")
  const topics: Topic[] = await response.json();
  console.log(topics);
  return topics
};

async function getPostsDtos(userId: number) : Promise<PostDto[]> {
  const response = await fetch(`http://localhost:8080/posts?subscriberId=${userId}`);
  const posts: PostDto[] = await response.json();
  console.log(posts);
  return posts;
}


export { getTopics, getPostsDtos }
