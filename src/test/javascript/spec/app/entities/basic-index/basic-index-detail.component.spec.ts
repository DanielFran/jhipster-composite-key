/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterTestModule } from '../../../test.module';
import { BasicIndexDetailComponent } from '../../../../../../main/webapp/app/entities/basic-index/basic-index-detail.component';
import { BasicIndexService } from '../../../../../../main/webapp/app/entities/basic-index/basic-index.service';
import { BasicIndex } from '../../../../../../main/webapp/app/entities/basic-index/basic-index.model';

describe('Component Tests', () => {

    describe('BasicIndex Management Detail Component', () => {
        let comp: BasicIndexDetailComponent;
        let fixture: ComponentFixture<BasicIndexDetailComponent>;
        let service: BasicIndexService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [BasicIndexDetailComponent],
                providers: [
                    BasicIndexService
                ]
            })
            .overrideTemplate(BasicIndexDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BasicIndexDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BasicIndexService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new BasicIndex(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.basicIndex).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
