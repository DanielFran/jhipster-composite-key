/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { JhiDateUtils } from 'ng-jhipster';

import { BusinessBasicIndexService } from '../../../../../../main/webapp/app/entities/business-basic-index/business-basic-index.service';
import { SERVER_API_URL } from '../../../../../../main/webapp/app/app.constants';

describe('Service Tests', () => {

    describe('BusinessBasicIndex Service', () => {
        let injector: TestBed;
        let service: BusinessBasicIndexService;
        let httpMock: HttpTestingController;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [
                    HttpClientTestingModule
                ],
                providers: [
                    JhiDateUtils,
                    BusinessBasicIndexService
                ]
            });
            injector = getTestBed();
            service = injector.get(BusinessBasicIndexService);
            httpMock = injector.get(HttpTestingController);
        });

        describe('Service methods', () => {
            it('should call correct URL', () => {
                service.find(11, 22, 33).subscribe(() => {});

                const req  = httpMock.expectOne({ method: 'GET' });

                const resourceUrl = SERVER_API_URL + 'api/business-basic-indices';
                expect(req.request.url).toEqual(resourceUrl + '/' + 11 + '/' + 22 + '/' + 33);
            });
            it('should return BusinessBasicIndex', () => {

                service.find(11, 22, 33).subscribe((received) => {
                    expect(received.body.businessId).toEqual(11) &&
                    expect(received.body.basicIndexId).toEqual(22) &&
                    expect(received.body.year).toEqual(33);
                });

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush({businessId: 11, basicIndexId: 22, year: 33});
            });

            it('should propagate not found response', () => {

                service.find(11, 22, 33).subscribe(null, (_error: any) => {
                    expect(_error.status).toEqual(404);
                });

                const req  = httpMock.expectOne({ method: 'GET' });
                req.flush('Invalid request parameters', {
                    status: 404, statusText: 'Bad Request'
                });

            });
        });

        afterEach(() => {
            httpMock.verify();
        });

    });

});
