import {Card, CardFooter, CardHeader, Persona, Image } from "@fluentui/react-components";
import { Post } from "../domain/Post";

type Props = {
    post: Post
}

export default function PostCard( { post }: Props) {
    return (
    <Card
        as="div"
        size="large"
        className="mb-10 p-0"
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
                  src={post.author.avatar}
                  alt={`${post.author.name} avatar`}
                  />
                }
                name = {post.author.name}
                secondaryText= {post.createdDate.toLocaleTimeString()}
                textAlignment="center"
              />
            <b>{post.subtopic ? `${post.topic.name}#${post.subtopic.name}` : post.topic.name}</b>
          </div>
        }
      />
      <div>
        <h2 className="text-base mb-1">{post.title}</h2>
        <p>{post.content}</p>
      </div>
      <CardFooter/>
    </Card>
    )
}