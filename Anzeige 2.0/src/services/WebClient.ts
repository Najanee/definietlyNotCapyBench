import { PostDto } from "../components/dto/PostDto";
import { Topic } from "../domain/Topic";

async function getTopics() : Promise<Topic[]> {
  try {
    const response = await fetch("http://localhost:8080/topics")
    const topics: Topic[] = await response.json();
    console.log(topics);
    return topics
  } catch (error) {
    console.log(error);
    return new Promise((resolve, reject) => undefined);
  }
  
};

async function getPostsDtos(userId: number) : Promise<PostDto[]> {
  try {
    const response = await fetch(`http://localhost:8080/posts?subscriberId=${userId}`);
    const posts: PostDto[] = await response.json();
    console.log(posts);

    return posts;

  } catch (error) {
    console.log(error);
    return new Promise((resolve, reject) => []);
  }
}

async function subscribeToResource(userId: number, resourceId: number, resourceType: string) {
  try {
    const response = await requestMethod(userId, resourceId, resourceType, "POST");
    response.status === 200 ? console.log(`Subscribed to ${resourceType}Id: ${resourceId}`) 
      : console.log(`Subscribe for ${resourceType}Id:${resourceId} unsuccessful.`);
  } catch (error) {
    console.log(error);
    console.log(`Subscribe for ${resourceType}Id:${resourceId} unsuccessful.`);

    return new Promise((resolve, reject) => undefined);
  }
}

async function unsubscribeFromResource(userId: number, resourceId: number, resourceType: string) {
  try {
    const response = await requestMethod(userId, resourceId, resourceType, "DELETE");
    response.status === 200 ? console.log(`Unsubscribed to ${resourceType}Id: ${resourceId}`) 
      : console.log(`Unsubscribe for ${resourceType}Id:${resourceId} unsuccessful. Status: ${response.status}`);
  } catch (error) {
    console.log(`Subscription request for ${resourceType}Id:${resourceId} unsuccessful: ${error}`);

    return new Promise((resolve, reject) => undefined);
  }
}

const requestMethod = (userId: number, resourceId: number, resourceType: string, methodType: string)  => {
  return fetch(`http://localhost:8080/subscription/${userId}?${resourceType}Id=${resourceId}`, {
    method: methodType,
    headers: {
      "Access-Control-Allow-Origin": "*"
    }
  });
}

const getData = async (userId: number) => {
  const topics = await getTopics();
  const subscribedSubtopics = topics.flatMap(topic => topic.subtopics)
    .filter(subtopic => subtopic.subscriberIds?.includes(userId));
  const subscribedTopics = topics.filter(topic => topic.subscriberIds?.includes(userId))
  const postDtos: PostDto[] = await getPostsDtos(userId);
  const posts = postDtos.map(postDto => 
    new PostDto(
      postDto.id, 
      postDto.title, 
      postDto.content, 
      postDto.createDate, 
      postDto.author, 
      postDto.expirationDate, 
      postDto.subscriberIds, 
      postDto.topicId, 
      postDto.subtopicId))
  .map(postDto => postDto.mapToDomain(topics, subscribedSubtopics));

  return {topics, subscribedTopics, subscribedSubtopics, posts};
}

export { getTopics, getPostsDtos, subscribeToResource, unsubscribeFromResource, getData}
