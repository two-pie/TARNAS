import { RNAFormat } from "./RNAFormat"

export interface RNAFile {
    fileName?: string;
    format?: RNAFormat;
    content?: string[];
}