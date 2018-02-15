/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterTestModule } from '../../../test.module';
import { BusinessBasicIndexDetailComponent } from '../../../../../../main/webapp/app/entities/business-basic-index/business-basic-index-detail.component';
import { BusinessBasicIndexService } from '../../../../../../main/webapp/app/entities/business-basic-index/business-basic-index.service';
import { BusinessBasicIndex } from '../../../../../../main/webapp/app/entities/business-basic-index/business-basic-index.model';

describe('Component Tests', () => {

    describe('BusinessBasicIndex Management Detail Component', () => {
        let comp: BusinessBasicIndexDetailComponent;
        let fixture: ComponentFixture<BusinessBasicIndexDetailComponent>;
        let service: BusinessBasicIndexService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [BusinessBasicIndexDetailComponent],
                providers: [
                    BusinessBasicIndexService
                ]
            })
            .overrideTemplate(BusinessBasicIndexDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BusinessBasicIndexDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BusinessBasicIndexService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new BusinessBasicIndex(11, 22, 33)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.businessBasicIndex).toEqual(jasmine.objectContaining({businessId: 11, basicIndexId: 22, year: 33}));
            });
        });
    });

});
