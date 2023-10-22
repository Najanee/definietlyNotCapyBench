import { useContext, useState, useEffect } from "react";
import { TeamsFxContext } from "./Context";
import config from "./sample/lib/config";
import { Button, Divider, Text } from "@fluentui/react-components";
// import { posts } from "../temporarydata/TemporaryData";
import { getPostsDtos, getTopics } from "../services/WebClient";
import { AddSquareMultiple16Regular } from "@fluentui/react-icons";
import { PostCard } from "./PostCard";
import { TopicBar } from "./TopicBar";
import { Topic } from "../domain/Topic";
import { Post } from "../domain/Post";
import { Subtopic } from "../domain/Subtopic";
import { PostDto } from "./dto/PostDto";

const showFunction = Boolean(config.apiName);

export function Tab() {
  const { themeString } = useContext(TeamsFxContext);
  const getTheme = () : string => themeString === "default" ? "light" : themeString === "dark" ? "dark" : "contrast";
  
  const USER_ID = 1;
  const [topics, setTopics] = useState<Topic[]>([]);
  const [subscribedTopics, setSubscribedTopics] : [Topic[], (value: Topic[]) => void] = 
    useState<Topic[]>([]);
  const [subscribedPosts, setSubscribedPosts] : [Post[], (value: Post[]) => void] = 
    useState<Post[]>([]);
  const [subscribedSubtopics, setSubscribedSubtopics] : [Subtopic[], (value: Subtopic[]) => void] = 
  useState<Subtopic[]>([]);

  const isSubscribed = (resource: Topic | Subtopic | Post) : Boolean => {
    return resource.subscriberIds?.includes(USER_ID);
  }
    
  useEffect(() => {
      const getData = async () => {
        const topics = await getTopics();
        const subscribedSubtopics = topics.flatMap(topic => topic.subtopics)
          .filter(subtopic => subtopic.subscriberIds?.includes(USER_ID));
        const subscribedTopics = topics.filter(topic => topic.subscriberIds?.includes(USER_ID))
        const postDtos: PostDto[] = await getPostsDtos(USER_ID);
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

        setTopics(topics);
        setSubscribedTopics(subscribedTopics)
        setSubscribedSubtopics(subscribedSubtopics)
        setSubscribedPosts(posts);
      }

      getData()
  }, [])

  return (
    <div className={`${getTheme()} max-w-screen-lg w-4/5 m-auto py-10`}>
      <div className="flex justify-center ">
        <div className="flex flex-col max-w-3xl items-center">
          {subscribedPosts.map(post => 
            <PostCard 
              post={post}
              isSubscribed={isSubscribed}
              subscribedPosts={subscribedPosts}
              setSubscribedPosts={setSubscribedPosts}
            />
          )}
        </div>
        <Divider vertical />
        <TopicBar
          topics={topics}
          isSubscribed={isSubscribed}
          subscribedSubtopics={subscribedSubtopics}
          subscribedTopics={subscribedTopics}
          setSubscribedSubtopics={setSubscribedSubtopics}
          setSubscribedTopics={setSubscribedTopics}
        />
      </div>

      <Button
        icon= {<AddSquareMultiple16Regular />}
        size="medium"
        appearance="primary"
        iconPosition="before"
        onClick={getTopics}
      >
        Create post
      </Button>
    </div>
  );
}
