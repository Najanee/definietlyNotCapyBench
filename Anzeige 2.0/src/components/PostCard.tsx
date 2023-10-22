import {Card, CardFooter, CardHeader, Persona, Image, Text, Divider, Button, Tag } from "@fluentui/react-components";
import { Post } from "../domain/Post";
import { Comment16Regular, Share16Regular, Star16Filled, StarAdd16Regular } from "@fluentui/react-icons";
import { Topic } from "../domain/Topic";
import { Subtopic } from "../domain/Subtopic";

type Props = {
    post: Post
    isSubscribed: (resource: Topic | Subtopic | Post) => Boolean;
    subscribedPosts: Post[];
    setSubscribedPosts: (value: Post[]) => void;
}

export function PostCard( { post, isSubscribed, subscribedPosts, setSubscribedPosts }: Props) {

  const toggleSubscribe = (handledPost: Post) : void => {
    if(isSubscribed(handledPost)) {
      const dimnishedPosts = [...subscribedPosts];
      dimnishedPosts.splice(subscribedPosts.findIndex(post => post.id === handledPost.id), 1)
      setSubscribedPosts(dimnishedPosts);
    } else {
      setSubscribedPosts([...subscribedPosts, handledPost]);
    }
  } 

    return (
    <Card
        as="div"
        size="large"
        className="mb-10 px-5 w-full"
        appearance="filled"
    >
      <CardHeader
        header={
          <div className="flex justify-between content-center w-full" >
              <Persona 
                avatar= {
                  <Image
                  shape="circular"
                  fit="cover"
                  src={post.author.imageUrl}
                  alt={`${post.author.name} avatar`}
                  />
                }
                name = {post.author.name}
                secondaryText= {post.createDate.toLocaleTimeString()}
                textAlignment="center"
              />
              <Tag>
                <Text 
                  weight="semibold"
                >
                  {post.subtopic ? `${post.topic.name} #${post.subtopic.name}` : post.topic.name}
                </Text>
              </Tag>
          </div>
        }
      />
      <div>
        <h2 className="text-base mb-1 font-medium">{post.title}</h2>
        <p>{post.content}</p>
      </div>
      <Divider />
      <CardFooter
        className="flex justify-between content-center"
      >
        <Text className="justify-self-start">Observed by {post.subscriberIds.length} people.</Text>
        <div className="justify-self-end flex content-center justify-between w-1/5">
          <Button 
            icon={isSubscribed(post) ? <Star16Filled /> : <StarAdd16Regular />}
            onClick={() => toggleSubscribe(post)}
          />
          <Button 
            icon={<Share16Regular />} 
          />
          <Button 
            icon={<Comment16Regular />} 
          />
        </div>
      </CardFooter>
    </Card>
    )
}