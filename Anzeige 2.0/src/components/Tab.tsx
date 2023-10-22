import { useContext, useState, useEffect } from "react";
import { TeamsFxContext } from "./Context";
import { Button, Divider } from "@fluentui/react-components";
import { getData, getTopics, subscribeToResource, unsubscribeFromResource } from "../services/WebClient";
import { AddSquareMultiple16Regular } from "@fluentui/react-icons";
import { PostCard } from "./PostCard";
import { TopicBar } from "./TopicBar";
import { Topic } from "../domain/Topic";
import { Post } from "../domain/Post";
import { Subtopic } from "../domain/Subtopic";

export function Tab() {
  const { themeString } = useContext(TeamsFxContext);
  const getTheme = () : string => themeString === "default" ? "light" : themeString === "dark" ? "dark" : "contrast";
  const USER_ID = 2;

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

  const toggleSubscribe = (resource: Topic | Subtopic | Post, resourceType: string) : void => {
    console.log(isSubscribed(resource));
    isSubscribed(resource) ? unsubscribeFromResource(USER_ID, resource.id, resourceType) 
      : subscribeToResource(USER_ID, resource.id, resourceType);
  } 
    
  useEffect(() => {
    getData(USER_ID).then(data => {
      setTopics(data.topics);
      setSubscribedTopics(data.subscribedTopics)
      setSubscribedSubtopics(data.subscribedSubtopics)
      setSubscribedPosts(data.posts);
    })
  }, [])

  return (
    <div className={`${getTheme()} max-w-screen-lg w-4/5 h-full m-auto py-10`}>
      <div className="flex justify-around">
        <div className="flex flex-col max-w-3xl items-center px-10 pb-10 relative">
            {subscribedPosts.map(post => 
              <PostCard
                key={post.id}
                userId={USER_ID}
                post={post}
                isSubscribed={isSubscribed}
                subscribedPosts={subscribedPosts}
                setSubscribedPosts={setSubscribedPosts}
                toggleSubscribe={toggleSubscribe}
              />
            )}
          <div className="blur-2xl w-full h-48 fixed bottom-0 left-0 bg-gray-400/20">
          </div>
          <div className="sticky bottom-5 left-0 mx-10 w-full h-10 bg-inherit">
            <Button
              icon= {<AddSquareMultiple16Regular />}
              size="medium"
              appearance="primary"
              iconPosition="before"
              onClick={getTopics}
            >
              New post
            </Button>
          </div>
        </div>
        <Divider vertical />
        <div className="relative">
          <TopicBar
            topics={topics}
            isSubscribed={isSubscribed}
            subscribedSubtopics={subscribedSubtopics}
            subscribedTopics={subscribedTopics}
            setSubscribedSubtopics={setSubscribedSubtopics}
            setSubscribedTopics={setSubscribedTopics}
            toggleSubscribe={toggleSubscribe}
          />
        </div>
      </div>
    </div>
  );
}
