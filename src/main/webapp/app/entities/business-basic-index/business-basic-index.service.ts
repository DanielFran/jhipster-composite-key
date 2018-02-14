import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { BusinessBasicIndex } from './business-basic-index.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<BusinessBasicIndex>;

@Injectable()
export class BusinessBasicIndexService {

    private resourceUrl =  SERVER_API_URL + 'api/business-basic-indices';

    constructor(private http: HttpClient) { }

    create(businessBasicIndex: BusinessBasicIndex): Observable<EntityResponseType> {
        const copy = this.convert(businessBasicIndex);
        return this.http.post<BusinessBasicIndex>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(businessBasicIndex: BusinessBasicIndex): Observable<EntityResponseType> {
        const copy = this.convert(businessBasicIndex);
        return this.http.put<BusinessBasicIndex>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<BusinessBasicIndex>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<BusinessBasicIndex[]>> {
        const options = createRequestOption(req);
        return this.http.get<BusinessBasicIndex[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<BusinessBasicIndex[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: BusinessBasicIndex = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<BusinessBasicIndex[]>): HttpResponse<BusinessBasicIndex[]> {
        const jsonResponse: BusinessBasicIndex[] = res.body;
        const body: BusinessBasicIndex[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to BusinessBasicIndex.
     */
    private convertItemFromServer(businessBasicIndex: BusinessBasicIndex): BusinessBasicIndex {
        const copy: BusinessBasicIndex = Object.assign({}, businessBasicIndex);
        return copy;
    }

    /**
     * Convert a BusinessBasicIndex to a JSON which can be sent to the server.
     */
    private convert(businessBasicIndex: BusinessBasicIndex): BusinessBasicIndex {
        const copy: BusinessBasicIndex = Object.assign({}, businessBasicIndex);
        return copy;
    }
}
