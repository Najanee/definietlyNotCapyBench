import { Person } from "../../domain/Person";
import { Post } from "../../domain/Post";
import { Subtopic } from "../../domain/Subtopic";
import { Topic } from "../../domain/Topic";

class PostDto {
    id: number;
    title: string;
    content: string;
    createDate: Date;
    author: Person;
    expirationDate: Date;
    subscriberIds: number[];
    topicId: number;
    subtopicId: number;

    constructor(id: number, title: string, 
                content: string, createDate: Date, 
                author: Person, expirationDate: Date,
                subsciberIds: number[], topicId: number, 
                subtopicId: number) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createDate = new Date(createDate);
        this.expirationDate = new Date(expirationDate);
        this.subscriberIds = subsciberIds;
        this.topicId = topicId;
        this.subtopicId = subtopicId;
    }

    mapToDomain(topics: Topic[], subtopics: Subtopic[]) : Post {
        return new Post(
            this.id,
            this.title,
            this.content,
            this.createDate,
            this.author,
            this.expirationDate,
            this.subscriberIds,
            topics.find(topic => topic.id === this.topicId)!,
            subtopics.find(subtopic => subtopic.id === this.subtopicId)
        );
    }
}

export { PostDto }