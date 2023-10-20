import { useContext } from "react";
import { TeamsFxContext } from "./Context";
import config from "./sample/lib/config";
import { Body1, Button, Card, CardFooter, CardHeader, Persona, ToggleButton } from "@fluentui/react-components";
import { tags, posts } from "../temporarydata/TemporaryData";
import { getTopics } from "../services/WebClient";

const showFunction = Boolean(config.apiName);

export default function Tab() {
  const { themeString } = useContext(TeamsFxContext);
  const getTheme = () : string => themeString === "default" ? "light" : themeString === "dark" ? "dark" : "contrast";

  return (
    <div
      className={`${getTheme()}`}
    >
      <div className = "flex justify-around py-5">
      {
        tags.map(tag => 
        <ToggleButton
        appearance="primary"
        shape="rounded"
        size="large"
        >
          {tag}
        </ToggleButton>)
      }
      </div>
      <div>
      {
        posts.map(post =>
          <Card>
            <CardHeader
              image={
                <img
                src={post.author.avatar}
                alt={`${post.author.name} avatar`}
                />
              }
              header={
                <Body1>
                  <text>{post.author.name}</text>
                </Body1>
              }
            />
            <CardFooter/>
          </Card>)
      }
      </div>
      <div className="flex justify-around py-5">
      <Button
      onClick={getTopics}
      >
        Dej Topic
      </Button>
      </div>
    </div>
  );
}
