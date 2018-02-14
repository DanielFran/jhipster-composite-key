/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterTestModule } from '../../../test.module';
import { BusinessBasicIndexComponent } from '../../../../../../main/webapp/app/entities/business-basic-index/business-basic-index.component';
import { BusinessBasicIndexService } from '../../../../../../main/webapp/app/entities/business-basic-index/business-basic-index.service';
import { BusinessBasicIndex } from '../../../../../../main/webapp/app/entities/business-basic-index/business-basic-index.model';

describe('Component Tests', () => {

    describe('BusinessBasicIndex Management Component', () => {
        let comp: BusinessBasicIndexComponent;
        let fixture: ComponentFixture<BusinessBasicIndexComponent>;
        let service: BusinessBasicIndexService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [BusinessBasicIndexComponent],
                providers: [
                    BusinessBasicIndexService
                ]
            })
            .overrideTemplate(BusinessBasicIndexComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BusinessBasicIndexComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BusinessBasicIndexService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new BusinessBasicIndex(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.businessBasicIndices[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
