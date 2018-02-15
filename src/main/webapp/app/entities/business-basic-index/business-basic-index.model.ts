import { BaseEntity } from './../../shared';

export class BusinessBasicIndex {
    constructor(
        public businessId?: number,
        public basicIndexId?: number,
        public year?: number,
        public businessName?: string,
        public basicIndexName?: string,
        public month?: number,
        public value?: number,
    ) {
    }
}
